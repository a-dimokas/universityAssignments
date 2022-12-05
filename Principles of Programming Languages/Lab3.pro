
%-----------------------------------------------------------------------------------------

%-- ASKHSH 1


twice(C,N) :- N>0,hosted(I1,Y1,C), Y2 is Y1+N, twiceHelp(Y2,C).

in2Cities(D) :- country(C1,D),country(C2,D),dif(C1,C2),hosted(I1,Y1,C1),hosted(I2,Y2,C2). 

in3Continents(I) :- hosted(I,Y,C1),country(C1,D1),continent(D1,E1),
			K is I+1,hosted(K,Y2,C2),country(C2,D2),continent(D2,E2),
			L is K+1,hosted(L,Y3,C3),country(C3,D3),continent(D3,E3),
			dif(E1,E2),dif(E1,E3),dif(E2,E3).



twiceHelp(Y,C) :- hosted(I,Y,C2),check(C,C2),twiceHelp(C,I+1).
check(C1,C2) :- C1 =:= C2,!,twiceHelp(Y,C).
twiceHelp(C,I) :- I<32,hosted(I,Y,C2),check(C,C2),twiceHelp(C,I+1).




%-----------------------------------------------------------------------------------------

%-- ASKHSH 2

move(A,B,C) :- C<0, fail.
move(A,B,C) :- fail.









%-----------------------------------------------------------------------------------------

%-- ASKHSH 3

f(N,M,Y) :- N=<0,Y is 0. 
f(N,M,Y) :- M=<0,Y is 0.
f(N,M,Y) :- M>N,Y is 0.
f(N,M,Y) :- M=:=1,Y is 1.
f(N,M,Y) :- M=:=N,Y is 1.
f(N,M,Y) :- M<3,K is M-1,O is N-M,f(O,M,Q),f(O,K,D),Y is D+Q.
f(N,M,Y) :- M<4,K is M-1,P is K-1,O is N-M,f(O,M,Q),f(O,K,D),f(O,P,E),Y is E+D+Q.
f(N,M,Y) :- M<5,K is M-1,P is K-1,O is N-M,f(O,M,Q),f(O,K,D),f(O,P,E),
	G is P-1,f(O,G,U),Y is U+E+D+Q.
f(N,M,Y) :- M<6,K is M-1,P is K-1,O is N-M,f(O,M,Q),f(O,K,D),f(O,P,E),
	G is P-1,f(O,G,U),R is G-1,f(O,R,X),Y is X+U+E+D+Q.
f(N,M,Y) :- M<7,K is M-1,P is K-1,O is N-M,f(O,M,Q),f(O,K,D),f(O,P,E),
	G is P-1,f(O,G,U),R is G-1,f(O,R,X),A is G-1,f(O,A,B),Y is B+X+U+E+D+Q.
f(N,M,Y) :- M<8,K is M-1,P is K-1,O is N-M,f(O,M,Q),f(O,K,D),f(O,P,E),
	G is P-1,f(O,G,U),R is G-1,f(O,R,X),A is G-1,f(O,A,B),
	C is A-1,f(O,C,F),Y is F+A+X+U+E+D+Q.


          

%-----------------------------------------------------------------------------------------

%-- ASKHSH 4

p(N) :- integer(N),N>0,sqrtInt(N,R),generatePrime(R,L),
	squareList(L,K),notDivisible(N,K).

notDivisible(X,[]).
notDivisible(X,[H|T]) :- K is X mod H, K =\= 0, notDivisible(X,T).

squareList([],[]).
squareList([H|T],[X|Y]):- X is H*H, squareList(T,Y).

insert(N,[],[N]).
insert(N,[H|T],[N,H|T]) :- N @=< H.
insert(N,[H|T],[H|S]) :- N@>H, insert(N,T,S).


generatePrime(1, []) :- !.
generatePrime(N, X) :- isNotPrime(N) ,!, Z is N-1, generatePrime(Z,X) .
generatePrime(N, [N|X]):- Z is N-1, generatePrime(Z,X) .

divisible(X,Y):- N is Y*Y,N =< X,X mod Y =:= 0.
divisible(X,Y):- Y < X,Y1 is Y+1,divisible(X,Y1).

isNotPrime(X):- Y is 2, X>1, divisible(X,Y).

not_divisible(X,Y):- N is Y*Y,N =< X,X mod Y \== 0.
not_divisible(X,Y):- Y < X,Y1 is Y+1,not_divisible(X,Y1).

isPrime(X):- Y is 2, X > 1, not_divisible(X,Y).

sqrtInt(N,R) :- sqrtHlp(N,0,N,R).
sqrtHlp(N,A,A,A).
sqrtHlp(N,A,B,R) :- A < B,C is (A+B+1)//2,K is C*C,
	newInterval(N,C,K,A,B,Anew,Bnew),sqrtHlp(N,Anew,Bnew,R).
newInterval(N,C,K,A,B,A,D) :- K > N, D is C-1.
newInterval(N,C,K,A,B,C,B) :- K =< N.


%-----------------------------------------------------------------------------------------
%-----------------------------------------------------------------------------------------
%-----------------------------------------------------------------------------------------

%-- MHN TROPOPOIHSETE TO PARAKATW TMHMA KWDIKA 

dif(X,Y) :- X \= Y.

hosted(1,1896,'Athens').
hosted(2,1900,'Paris').
hosted(3,1904,'St. Louis').
hosted(4,1908,'London').
hosted(5,1912,'Stockholm').
hosted(7,1920,'Antwerp').
hosted(8,1924,'Paris').
hosted(9,1928,'Amsterdam').
hosted(10,1932,'Los Angeles').
hosted(11,1936,'Berlin').
hosted(14,1948,'London').
hosted(15,1952,'Helsinki').
hosted(16,1956,'Melbourne').
hosted(17,1960,'Rome').
hosted(18,1964,'Tokyo').
hosted(19,1968,'Mexico City').
hosted(20,1972,'Munich').
hosted(21,1976,'Montreal').
hosted(22,1980,'Moscow').
hosted(23,1984,'Los Angeles').
hosted(24,1988,'Seoul').
hosted(25,1992,'Barcelona').
hosted(26,1996,'Atlanta').
hosted(27,2000,'Sydney').
hosted(28,2004,'Athens').
hosted(29,2008,'Beijing').
hosted(30,2012,'London').
hosted(31,2016,'Rio de Janeiro').


country('Melbourne','Australia').
country('Sydney','Australia').
country('Antwerp','Belgium').
country('Rio de Janeiro','Brazil').
country('Montreal','Canada').
country('Beijing','China').
country('Helsinki','Finland').
country('Paris','France').
country('Berlin','Germany').
country('Athens','Greece').
country('Rome','Italy').
country('Tokyo','Japan').
country('Mexico City','Mexico').
country('Amsterdam','Netherlands').
country('Seoul','South Korea').
country('Moscow','Soviet Union').
country('Barcelona','Spain').
country('Stockholm','Sweden').
country('London','United Kingdom').
country('St. Louis','United States of America').
country('Los Angeles','United States of America').
country('Atlanta','United States of America').
country('Munich','West Germany').

continent('Australia','Oceania').
continent('Belgium','Europe').
continent('Brazil','America').
continent('Canada','America').
continent('China','Asia').
continent('Finland','Europe').
continent('France','Europe').
continent('Germany','Europe').
continent('Greece','Europe').
continent('Italy','Europe').
continent('Japan','Asia').
continent('Mexico','America').
continent('Netherlands','Europe').
continent('South Korea','Asia').
continent('Soviet Union','Europe').
continent('Spain','Europe').
continent('Sweden','Europe').
continent('United Kingdom','Europe').
continent('United States of America','America').
continent('West Germany','Europe').



nextStation(green,X,Y) :- green(X,Y).
nextStation(red,X,Y) :- red(X,Y).
nextStation(blue,X,Y) :- blue(X,Y).
nextStation(yellow,X,Y) :- yellow(X,Y).

entranceFare(blue,10).
entranceFare(green,10).
entranceFare(red,60).
entranceFare(yellow,20).

travelFare(blue,15).
travelFare(green,15).
travelFare(red,5).
travelFare(yellow,8).


green(a1,b1).
green(b1,b2).
green(b2,c3).
green(c3,c4).
green(c4,b4).
green(b4,b5).
green(b5,b6).
green(b6,b7).
green(b7,a8).
green(a8,b8).
green(b8,c8).
green(b1,a1).
green(b2,b1).
green(c3,b2).
green(c4,c3).
green(b4,c4).
green(b5,b4).
green(b6,b5).
green(b7,b6).
green(a8,b7).
green(b8,a8).
green(c8,b8).
yellow(b1,c1).
yellow(c1,d1).
yellow(d1,e1).
yellow(e1,d2).
yellow(d2,d3).
yellow(d3,c4).
yellow(c4,d4).
yellow(d4,e4).
yellow(e4,f5).
yellow(f5,e5).
yellow(e5,d5).
yellow(d5,d6).
yellow(d6,c7).
yellow(c7,c8).
yellow(c8,d8).
yellow(d8,e8).
yellow(e8,f8).
yellow(c1,b1).
yellow(d1,c1).
yellow(e1,d1).
yellow(d2,e1).
yellow(d3,d2).
yellow(c4,d3).
yellow(d4,c4).
yellow(e4,d4).
yellow(f5,e4).
yellow(e5,f5).
yellow(d5,e5).
yellow(d6,d5).
yellow(c7,d6).
yellow(c8,c7).
yellow(d8,c8).
yellow(e8,d8).
yellow(f8,e8).
blue(d2,f1).
blue(f1,g1).
blue(g1,h1).
blue(h1,g2).
blue(g2,g3).
blue(g3,g4).
blue(g4,f5).
blue(f5,f6).
blue(f6,f7).
blue(f7,f8).
blue(f8,g7).
blue(g7,h8).
blue(f1,d2).
blue(g1,f1).
blue(h1,g1).
blue(g2,h1).
blue(g3,g2).
blue(g4,g3).
blue(f5,g4).
blue(f6,f5).
blue(f7,f6).
blue(f8,f7).
blue(g7,f8).
blue(h8,g7).
red(a2,b3).
red(b3,c3).
red(c3,d3).
red(d3,e3).
red(e3,e2).
red(e2,f2).
red(f2,f3).
red(f3,g3).
red(g3,h2).
red(h2,h3).
red(h3,h4).
red(h4,h5).
red(h5,h6).
red(h6,h7).
red(h7,g6).
red(g6,f6).
red(f6,e7).
red(e7,d7).
red(d7,d6).
red(d6,c6).
red(c6,b6).
red(b6,a7).
red(a7,a6).
red(a6,a5).
red(a5,a4).
red(a4,a3).
red(a3,a2).
red(b3,a2).
red(c3,b3).
red(d3,c3).
red(e3,d3).
red(e2,e3).
red(f2,e2).
red(f3,f2).
red(g3,f3).
red(h2,g3).
red(h3,h2).
red(h4,h3).
red(h5,h4).
red(h6,h5).
red(h7,h6).
red(g6,h7).
red(f6,g6).
red(e7,f6).
red(d7,e7).
red(d6,d7).
red(c6,d6).
red(b6,c6).
red(a7,b6).
red(a6,a7).
red(a5,a6).
red(a4,a5).
red(a3,a4).
red(a2,a3).
