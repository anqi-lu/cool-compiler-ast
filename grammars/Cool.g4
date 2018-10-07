/* Grammar for the Cool-W compiler, CS4533 and CS544, Worcester Polytechnic Institute
 * Author: Anqi Lu
 */
grammar Cool;

@header {
package cool.lexparse;
}


// Parser rules
coolText              : classes+=classDef+ EOF ;

// (INHERITS inherits+=TYPE)? 
classDef              : (CLASS type=TYPE (INHERITS inherits=TYPE)? '{' features=feature '}' ';');

feature               : ((methods+=method ';') | (variables+=variable ';'))*;

method                : name=ID '(' (paramaters+=formal (',' paramaters+=formal)*)* ')' ':' type=TYPE '{' body=expr '}' ;
variable              : id=ID ':' type=TYPE (ASSIGN value=expr)? ;
                      
formal                : id=ID ':' type=TYPE ;

expr 			      : object=expr'.'methodName=ID'('(args+=expr (',' args+=expr)*)?')'           #methodCallExpr
					  | methodName=ID'('(args+=expr (',' args+=expr)*)?')'                         #methodCallExpr
                      | IF cond=expr THEN thenExpr=expr ELSE elseExpr=expr FI                      #ifExpr
                      | WHILE expr LOOP expr POOL                                                  #whileExpr
					  | '{' (exprs+=expr ';')+ '}'                                                 #exprList
					  | LET ID ':' TYPE ( ASSIGN expr)? (',' ID ':' TYPE ( ASSIGN expr)?)* IN expr #letExpr
                      | CASE exp=expr OF alts+=caseAltExpr+ ESAC                                   #caseExpr
                      | NEW TYPE                                                                   #newExpr
					  | op=ISVOID exp=expr                                                         #isvoidExpr
					  | left=expr op=(MULTIPLY | DIVIDE) right=expr                                #binaryExpr 
					  | left=expr op=(PLUS | MINUS) right=expr                                     #binaryExpr
					  | <assoc=right> left=expr op=(EQUAL | NOT_EQUAL) right=expr                  #binaryExpr
					  | left=expr  op=(LESS_THAN 
					  				| LESS_OR_EQUAL	
					  				| GREATER_OR_EQUAL 
					  				| GREATER_THAN) 
					  	right=expr                                                                 #binaryExpr
					  | '(' exp=expr ')'                                                           #unaryExpr
					  | op=NOT exp=expr                                                            #unaryExpr
					  | id=ID ASSIGN value=expr                                                    #assignExpr
				   	  | op=MINUS exp=expr                                                          #unaryExpr
				   	  | term                                                                       #terminal
				   	  ;
		
caseAltExpr           : (id=ID ':' type=TYPE '=>' exp=expr';');				
term                  : idTerm=ID      
					  | intTerm=INTEGER 
					  | strTerm=STRING
					  | boolTerm=TRUE
					  | boolTerm=FALSE
					  ;

// Lexer rules:
// We assume that reserved words are recognized by the scanner rather than just
// recognizing an identifier and letting the parser determine the token class.

// Keywords
CLASS                 : 'class' ;
ELSE                  : 'else' ;
FALSE                 : 'false' ;
FI                    : 'fi' ; 
IF                    : 'if' ;
IN                    : 'in' ;
INHERITS              : 'inherits' ;
ISVOID                : 'isvoid' ;
LET                   : 'let' ;
LOOP                  : 'loop' ;
POOL                  : 'pool' ; 
THEN                  : 'then' ;
WHILE                 : 'while' ;
CASE                  : 'case' ;
ESAC                  : 'esac' ;
NEW                   : 'new' ;
OF                    : 'of' ;
TRUE                  : 'true' ;


// Integers, Identifiers 
ID                    : [a-z] [_0-9A-Za-z]* ; 
INTEGER               : [0-9]+ ; 
TYPE                  : [A-Z] [_0-9A-Za-z]* ; 

// Whitespace and null
WS                    : [ \t\r\n\f\b]+ -> skip;
NULL                  : [\u0000] ;

// Comments
COMMENT1              : '#' .*? ('\n' | EOF) -> skip ;  
COMMENT2              : '(*' (COMMENT2 | .)*? '*)' -> skip ; 
LINE_COM              : '#' ;
OPEN_COM              : '(*' ; 
CLOSE_COM             : '*)' ;

// Strings
STRING                : '"' (~([\n]) | VALID_ESC)* '"' ; 

INVAL_ESC             : WS | NULL ;
VALID_ESC             : ('\\' ~[INVAL_ESC]) ;


// Operation symbols 
 ASSIGN               : '<-' ;
 CASE_ARROW           : '=>' ;
 PLUS                 : '+' ;
 MINUS                : '-' ;
 MULTIPLY             : '*' ;
 DIVIDE               : '/' ;
 NOT                  : '~' ;
 
 // Comparison symbols 
 EQUAL                : '=' ;
 LESS_THAN            : '<' ;
 LESS_OR_EQUAL        : '<=' ;
 NOT_EQUAL            : '~=' ;
 GREATER_OR_EQUAL     : '>=' ;
 GREATER_THAN         : '>' ;
 
 // Others
 COLN                 : ':' ;
 LBRAC                : '{' ;
 RBRAC                : '}' ;
 LPARN                : '(' ;
 RPARN                : ')' ;
 COMMA                : ',' ;
 SEMIC                : ';' ; 
 SIGN                 : '@' -> skip ;

 
 
