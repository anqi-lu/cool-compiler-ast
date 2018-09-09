/* Grammar for the Cool-W compiler, CS4533 and CS544, Worcester Polytechnic Institute
 * Author: Anqi Lu
 */
grammar Cool;

@header {
package cool.lexparse;
}

// Parser rules
program			      :	(classDef ';')+ EOF ;


classDef              : CLASS TYPE (INHERITS TYPE)? LBRAC (feature';')* RBRAC;

feature 
                      : ID '(' (formal (',' formal)* ) ')' ':' TYPE '{' expr '}'
                      | ID ':' TYPE (ASSIGN expr)?;

formal                : ID ':' TYPE ;

expr 
                      : ID ASSIGN expr  
                      | expr'.'ID'('(expr (',' expr)*)')'
                      | ID'('(expr (',' expr)*)')'
                      | IF expr THEN expr ELSE expr FI
                      |  WHILE expr LOOP expr
                      | '{' (expr ';')+ '}'
                      | LET ID ':' TYPE ( ASSIGN expr)? (',' ID ':' TYPE ( ASSIGN expr)?)* IN expr
                      | CASE expr OF (ID ':' TYPE '=>' expr',')+ ESAC
                      | NEW TYPE
                      | ISVOID expr 
                      | expr PLUS expr
                      | expr MINUS expr
                      | expr MULTIPLY expr
                      | expr DIVIDE expr
                      | MINUS expr
                      | expr LESS_THAN expr
                      | expr LESS_OR_EQUAL expr
                      | expr EQUAL expr
                      | expr NOT_EQUAL expr
                      | expr GREATER_OR_EQUAL expr
                      | expr GREATER_THAN expr 
                      | NOT expr
                      | '(' expr ')'
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
INTEGER               : MINUS?[0-9]+ ; 
TYPE                  : [A-Z] [_0-9A-Za-z]* ; 

// Whitespace and null
WS                    : [ \t\r\n\f\b]+ -> skip;
NULL                  : [\u0000] ;


// Strings
STRING                : '"' (~([\n]) | VALID_ESC)* '"' ; 

INVAL_ESC             : WS | NULL ;
VALID_ESC             : ('\\' ~[INVAL_ESC]) ;

 

// Comments
COMMENT               : COMMENT1 | COMMENT2 ;
COMMENT1              : '##' .*? '\n' ;  
COMMENT2              : OPEN_COM (COMMENT2 | .)*? CLOSE_COM ; 

OPEN_COM             : '(*' ; 
CLOSE_COM            : '*)' ;

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
 OTHER                : [:{}(),;] -> skip ;
 COLN                 : ':' ;
 LBRAC                : '{' ;
 RBRAC                : '}' ;
 LPARN                : '(' ;
 RPARN                : ')' ;
 COMMA                : ',' ;
 SEMIC                : ';' ; 

 
 
