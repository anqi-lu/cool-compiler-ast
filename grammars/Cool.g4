/* Grammar for the Cool-W compiler, CS4533 and CS544, Worcester Polytechnic Institute
 * Author: <your name goes here>
 */
grammar Cool;

@header {
package cool.lexparse;
}

// Parser rules
// Just two rules for now to allow compilation.
program			:	classDef+ EOF ;

classDef			:	CLASS ;


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
BLANK  : [\u0020] ;

ID                    : [a-zA-Z]+ ;
INTEGER               : [0-9]+ ;

// Strings
// escapes that are not valid in a string as a character 
NEWLINE               : [\n] ;
BACKSPACE             : [\b] ;
TAB                   : [\t] ;
FORMFEED              : [\f] ;
CARRETURN             : [\r] ;
NULL                  : [\u0000] ;



// Whitespace
WS                    : BLANK | NEWLINE | TAB | FORMFEED | CARRETURN;

// escapes that are not characters
INVAL_ESC             : WS | NULL;
// valid characters with '\' such as '\c'
VALID_ESC             : ('\\' ~[INVAL_ESC]) ;
// valid characters. A string shouldn't have '"' in it
VALID_CHAR            : VALID_ESC | ~('\\' | '"') ;
// An empty string is valid.
STRING                : '"' VALID_CHAR* '"' ; 

// Comments. There are two types. 
COMMENT               : COMMENT1 | COMMENT2 ;
COMMENT1              : '##' .*? '\n' ; // -> type(COMMENT) ;// between 2 '#'s and a new line 
COMMENT2              : '(*' (COMMENT2 | .)*? '*)' ; // -> type(COMMENT) ; // nested comments


