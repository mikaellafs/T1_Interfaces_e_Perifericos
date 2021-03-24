grammar Arithmetic;

// ----- Parser Rules -----------

operation:
		NUMBER '+' NUMBER
	|	NUMBER '-' NUMBER
	|	NUMBER '*' NUMBER
	|	NUMBER '/' NUMBER
	;

// ----- Lexer Rules ------------

fragment
DIGIT:
		'0' .. '9'
	;

NUMBER:
		DIGIT+
	// |	DIGIT+ '.' DIGIT+
	;

WS:
		(' ' | '\t' | '\r' | '\n')+
		-> skip
	;