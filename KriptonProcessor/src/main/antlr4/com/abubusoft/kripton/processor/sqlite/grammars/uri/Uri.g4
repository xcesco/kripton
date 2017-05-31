/*
BSD License

Copyright (c) 2016, Tom Everett
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.
3. Neither the name of Tom Everett nor the names of its contributors
   may be used to endorse or promote products derived from this software
   without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
/**
* scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
*/

grammar Uri;

bind_parameter
 : '$' '{' SPACES* bind_parameter_name SPACES* '}'
 ;

bind_parameter_name 
 : STRING
 ;
 
bind_parameter_text 
 : '*'
 ;

bind_parameter_number 
 : '#'
 ;
  
 
uri
   : url
   ;

url
   : K_CONTENT '://' authority '/' (path)?
   ;

authority
   : STRING
   ;

protocol
   : K_CONTENT
   ;

path
   : path_segment ('/' path_segment)*
   ;

path_segment
   : bind_parameter |  bind_parameter_number | bind_parameter_text | STRING
   ;


K_CONTENT
 : C O N T E N T ;

HEX
    : ('%' [a-fA-F0-9] [a-fA-F0-9])+
    ;

STRING
   : ([a-zA-Z0-9_.]| HEX)*
   ;


DIGITS
   : [0-9] +
   ;

SPACES
 : [ \u000B\t\r\n] -> channel(HIDDEN)
 ;


WS
   : [\r\n] +
   ;
   
fragment DIGIT : [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];