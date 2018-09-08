/* Grammar for the Cool-W compiler, CS4533 and CS544, Worcester Polytechnic Institute
 * Author: <your name goes here>
 */
grammar Cool;

@header {
package cool.lexparse;
}

// Parser rules
/* 
 * For this part I decided not to replace all the symbols as the rules for better readability
 * I think - { expr } is easier to read than LBRAC expr RBRAC
 */
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



ID                    : [a-z] [_0-9A-Za-z]* ; // has to start with lower case letters
INTEGER               : MINUS?[0-9]+ ; // could be positive or negative 
TYPE                  : [A-Z] [_0-9A-Za-z]* ; // start with upper case letters


WS                    : [ \t\r\n\f\b]+ -> skip;
NULL                  : [\u0000] ;


/* An empty string is valid. VALID_CHAR is defined at the bottom 
   to avoid conflict with other rules */
STRING                : '"' (~([\n]) | VALID_ESC)* '"' ; 
 
/* used for matching strings 
 * putting these at the bottom so they don't get matched before others 
 */
 
// escapes that are not characters
INVAL_ESC             : WS | NULL ;

// valid characters with '\' such as '\c', but these should not be white-spaces or null
VALID_ESC             : ('\\' ~[INVAL_ESC]) ;

 

// Comments. There are two types. 
COMMENT               : COMMENT1 | COMMENT2 ;
COMMENT1              : '##' .*? '\n' ; // between 2 '#'s and a new line 
COMMENT2              : OPEN_COM (COMMENT2 | .)*? CLOSE_COM ; // nested comments, non-greedy

// defining these so * don't get matched with left paran and multiply 
OPEN_COM             : '(*' ; 
CLOSE_COM            : '*)' ;

// assignment operator
 ASSIGN               : '<-' ;

// operation symbols 
 PLUS                 : '+' ;
 MINUS                : '-' ;
 MULTIPLY             : '*' ;
 DIVIDE               : '/' ;
 NOT                  : '~' ;
 
 // TODO: deal with the ambiguity of two minuses 
 
 // comparison symbols 
 EQUAL                : '=' ;
 LESS_THAN            : '<' ;
 LESS_OR_EQUAL        : '<=' ;
 NOT_EQUAL            : '~=' ;
 GREATER_OR_EQUAL     : '>=' ;
 GREATER_THAN         : '>' ;
 
 SYMBOLS              : [:{}(),;] -> skip ;
 COLN                 : ':' ;
 LBRAC                : '{' ;
 RBRAC                : '}' ;
 LPARN                : '(' ;
 RPARN                : ')' ;
 COMMA                : ',' ;
 SEMIC                : ';' ; 
 CASE_ARROW           : '=>' ;
 
 
