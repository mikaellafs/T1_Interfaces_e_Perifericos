grammar Arithmetic;

// ----- Parser Rules -----------

expression:
		numberExpression operation NUMBER
	|	numberExpression operation '(' numberExpression ')'
	|	'(' numberExpression ')' operation NUMBER
	|	'(' numberExpression ')' operation '(' numberExpression ')'
	;

operation:
		PLUS
	|	MINUS
	|	TIMES
	|	OVER
	;

numberExpression:
		NUMBER
	|	PLUS NUMBER
	|	MINUS NUMBER
	;

// ----- Lexer Rules ------------

fragment
DIGIT:
		'0' .. '9'
	;

// Se não me engano tem um paranaue do antlr pra retirar
// possiveis 0's excedentes...
NUMBER:
		DIGIT+
	;

PLUS:
		'+'
	;

MINUS:
		'-'
	;

TIMES:
		'*'
	;

OVER:
		'/'
	;

WS:
		(' ' | '\t' | '\r' | '\n')+
		-> skip
	;