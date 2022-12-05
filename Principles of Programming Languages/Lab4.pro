
%-----------------------------------------------------------------------------------------

%-- ASKHSH 1


appendToList(X, [], [X]).
appendToList(X, Y, [X|Y]).

copy(L,R) :- copyHelp(L,R).
copyHelp([],[]).
copyHelp([H|T1],[H|T2]) :- copyHelp(T1,T2).

dotHelp(0,M,K,L,Q):- copy(Q,L).
dotHelp(1,M,K,L,Q):- appendToList(M,Q,R), copy(R,L).
dotHelp(N,M,K,L,Q):- N1 is N-1, Nk is N1*K, O is M+Nk, 
	appendToList(O,Q,R), dotHelp(N1,M,K,L,R).

...(N,M,K,L):- dotHelp(N,M,K,L,[]).


%-----------------------------------------------------------------------------------------

%-- ASKHSH 2

common([],Y,L):- copy([],L).
common(X,[],L):- copy([],L).
common([X1|X2],[Y1|Y2],L) :- commonHelp([X1|X2],[Y1|Y2],L,1,[]).

commonHelp([],Y,L,K,Q):- copy(Q,L).
commonHelp(X,[],L,K,Q):- copy(Q,L).
commonHelp([],[],L,K,Q):- copy(Q,L).
commonHelp([X1|X2],[X1|Y],L,K,Q):- insert(K,Q,R), P is K+1,
	commonHelp(X2,Y,L,P,R).
commonHelp([X1|X2],[Y1|Y2],L,K,Q):- X1 =\= Y1, P is K+1,
	 commonHelp(X2,Y2,L,P,Q).

insert(N,[],[N]).
insert(N,[H|T],[N,H|T]) :- N @=< H.
insert(N,[H|T],[H|S]) :- N @> H,insert(N,T,S).



%-----------------------------------------------------------------------------------------

%-- ASKHSH 3

freq([],S):- copy([],S).
freq([H|T],S) :- freqHelp([H|T],S,[]).

freqHelp([],L,Q):- copy(Q,L).
freqHelp([H|T],L,Q) :- count2([H|T],H,N), appendToList(N*H,Q,R),
	removeInstances(H,[H|T],P), freqHelp(P,L,R).

count2([],X,0).
count2([X|T],X,Y):- count2(T,X,Z), Y is 1+Z.
count2([X1|T],X,Z):- X1\=X,count2(T,X,Z).

removeInstances(_,[],[]).
removeInstances(X,[X|Y],L):- removeInstances(X,Y,L), ! .
removeInstances(X,[H|Y],[H|L]):- removeInstances(X,Y,L).


%-----------------------------------------------------------------------------------------

%-- ASKHSH 4

count([],C) :- C is 0.
count([H|T],C) :- checkPos([H|T]), recurse(H,T,C).

checkPos([]).
checkPos([H|T]):- integer(H), H @> 0, checkPos(T).

recurse(X,[],Z):- Z is 0.
recurse(X,[H|T],Z):- compare1(X,[H|T],P), recurse(H,T,K), Z is P+K.

compare1(X,[],Z):- Z is 0.
compare1(X,[H|T],Z):- X > 2*H, compare1(X,T,P), Z is P+1.
compare1(X,[H|T],Z):- H > 2*X, compare1(X,T,P), Z is P+1.
compare1(X,[H|T],Z):- compare1(X,T,P), Z is 0+P.

