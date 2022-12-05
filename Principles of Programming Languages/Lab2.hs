
-----------------------------------------------------------------------------------------

-- ASKHSH 1


nearest :: [Int]->Int->Int
nearest (h:t) n = findpos (h:t) (findclosest (h:t) n) 1

findclosest :: [Int]->Int->Int
findclosest (h:[]) n = h
findclosest a@(h:t) n 
	| abs (h - n) == 0 
		= h
	| abs (h - n) > abs ((last (h:t)) - n)
		= findclosest t n
	| otherwise
		= findclosest (init a) n

findpos :: [Int]->Int->Int->Int
findpos (h:[]) n k = k
findpos (h:t) n k
	| h == n
		= k
	| otherwise 
		= findpos t n (k+1)


-----------------------------------------------------------------------------------------
     
-- ASKHSH 2

smooth :: [Int]->Int->[Int]
smooth [] k = []
smooth s@(h:t) k 
	| length s < k
		= []
	|otherwise 
		= sumIntList (elemIntList k s) `div` k : smooth (tail s) k

sumIntList :: [Int] -> Int
sumIntList (h:t) = h + sumIntList t
sumIntList [] = 0

elemIntList :: Int -> [Int] -> [Int]
elemIntList 1 (h:t) = h : []
elemIntList n (h:t) = h : elemIntList (n-1) t
elemIntList n [] =
		error "elemIntList: index out of range"

-----------------------------------------------------------------------------------------
     
-- ASKHSH 3

swap :: String->String
swap [] = []
swap s@(x:xs)
	|findWords s == 1 = s
	|(findWords s) `mod` 2 == 0
		 = unWords (swapWords (split s))
	|otherwise
		= unWords (swapWords (init (split s))) ++ " " ++ last (split s)

unWords :: [String] -> String
unWords [] =  ""
unWords (w:ws) = w ++ go ws
  where
    go []     = ""
    go (v:vs) = (v ++ go vs)

swapWords :: [String]->[String]
swapWords [] = []
swapWords s@(x:y:xs) 
	| length s == 1 = s
	| length s == 2 = (y ++ " " ++ x) : []
	| otherwise
		= (y ++ " " ++ x) : " " : swapWords xs

split :: String->[String]
split [] = [""]
split s@(x:xs) 
	| isSpace x = "" : rest
	|otherwise	= (x : head rest) : tail rest
		where rest = split xs

isSpace :: Char->Bool
isSpace a
	| a == ' ' 
		= True
	|otherwise
		= False

findWords :: String->Int
findWords [] = 1
findWords s@(x:xs)
	| isSpace x
		= 1 + findWords xs
	|otherwise
		= findWords xs


-----------------------------------------------------------------------------------------
     
-- ASKHSH 4

mapi :: [u]->(u->Int->v)->[v]

mapi s f = hell s f 1 (length s)



hell :: [u]->(u->Int->v)->Int->Int->[v]
hell s@(x:xs) f k n
	| k == 1
		= [f x k] ++ hell xs f (k+1) n
	| k < n
		= [f x k] ++ hell xs f (k+1) n
	| k == n
		= [f x k]




