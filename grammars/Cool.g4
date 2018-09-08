/* Grammar for the Cool-W compiler, CS4533 and CS544, Worcester Polytechnic Institute
 * Author: <your name goes here>
 */
grammar Cool;

@header {
package cool.lexparse;
}

// Parser rules
// Just two rules for now to allow compilation.
program			      :	(classDef ';')+ EOF ;



classDef              : CLASS TYPE (INHERITS TYPE)? '{' (feature';')* '}';

feature 
                      : ID '(' (formal (',' formal)* ) ')' ':' TYPE '{' expr '}'
                      | ID ':' TYPE ('<-' expr)?;

formal                : ID ':' TYPE ;

expr 
                      : ID '<-' expr  
                      | expr'.'ID'('(expr (',' expr)*)')'
                      | ID'('(expr (',' expr)*)')'
                      | IF expr THEN expr ELSE expr FI
                      |  WHILE expr LOOP expr
                      | '{' (expr ';')+ '}'
                      | LET ID ':' TYPE ( '<-' expr)? (',' ID ':' TYPE ( '<-' expr)?)* IN expr
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


//Whitespace 
BLANK                 : [\u0020] ;

ID                    : [a-z] [_0-9A-Za-z]* ; // has to start with letters
INTEGER               : [0-9]+ ;
TYPE                  : [A-Z] [_0-9A-Za-z]* ;

// Strings
// escapes that are not valid in a string as a character 
NEWLINE               : [\n] ;
BACKSPACE             : [\b] ;
TAB                   : [\t] ;
FORMFEED              : [\f] ;
CARRETURN             : [\r] ;
NULL                  : [\u0000] ;

// Whitespace
WS                    : BLANK | TAB | FORMFEED | CARRETURN | NEWLINE;

// escapes that are not characters
INVAL_ESC             : WS | NULL ;
// valid characters with '\' such as '\c', but these should not be white-spaces or null
VALID_ESC             : ('\\' ~[INVAL_ESC]) ;

// valid characters. 
VALID_CHAR            : ~([\n]) | VALID_ESC ;
// An empty string is valid.
STRING                : '"' VALID_CHAR* '"' ; 

// Comments. There are two types. 
COMMENT               : COMMENT1 | COMMENT2 ;
COMMENT1              : '##' .*? '\n' ; // -> type(COMMENT) ;// between 2 '#'s and a new line 
COMMENT2              : '(*' (COMMENT2 | .)*? '*)' ; // -> type(COMMENT) ; // nested comments

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
