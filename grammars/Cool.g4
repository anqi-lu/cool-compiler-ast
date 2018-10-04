/* Grammar for the Cool-W compiler, CS4533 and CS544, Worcester Polytechnic Institute
 * Author: Anqi Lu
 */
grammar Cool;

@header {
package cool.lexparse;
}


// Parser rules
coolText               : (classDef ';')+ EOF ;

classDef              : CLASS TYPE (INHERITS TYPE)? '{' (feature';')* '}';

feature 
                      : ID '(' (formal (',' formal)*)* ')' ':' TYPE '{' expr '}'
                      | ID ':' TYPE (ASSIGN expr)? ;

formal                : ID ':' TYPE ;

expr 			      :  expr'.'ID'('(expr (',' expr)*)?')'
					  | ID'('(expr (',' expr)*)?')'
                      | IF expr THEN expr ELSE expr FI
                      | WHILE expr LOOP expr POOL
					  | '{' (expr ';')+ '}'
					  | LET ID ':' TYPE ( ASSIGN expr)? (',' ID ':' TYPE ( ASSIGN expr)?)* IN expr
                      | CASE expr OF (ID ':' TYPE '=>' expr';')+ ESAC
                      | NEW TYPE
					  | ISVOID expr
					  | expr multExpr expr
					  | expr plusExpr expr
					  | <assoc=right> expr (EQUAL | NOT_EQUAL) expr
					  | expr compExpr expr
					  | NOT expr
					  | ID ASSIGN expr
				   	  | term 
				   	  | MINUS* term;
						
multExpr              : MULTIPLY | DIVIDE ;
plusExpr              : PLUS | MINUS ;
compExpr              : LESS_THAN 
					  | LESS_OR_EQUAL	
					  | GREATER_OR_EQUAL 
					  | GREATER_THAN ;

term                  : '(' expr ')'        
                      | ID 
					  | INTEGER 
					  | STRING 
					  | TRUE 
					  | FALSE ;


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

 
 
