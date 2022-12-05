
-----------------------------------------------------------------------------------------

-- ASKHSH 1

count :: Integer -> Int

count n 
	| n < 0
		= count (-n)
	| (n `div` 10) == 0 && ((n == 0 || n == 3 || n == 6 || n == 9))
			= 1
	| (n `div` 10) == 0 
			= 0
	| (n `mod` 10) == 0 
		= 1 + count (n `div` 10)
	| (n `mod` 10) == 3 
		= 1 + count (n `div` 10)
	| (n `mod` 10) == 6 
		= 1 + count (n `div` 10)
	| (n `mod` 10) == 9 
		= 1 + count (n `div` 10)
	| otherwise 
		= count (n `div` 10)
			




-----------------------------------------------------------------------------------------
     
-- ASKHSH 2

kgcd :: Int->Int->Int->Int
kgcd m n k 
	= elemIntList k (reverse (commonDivList (divList m 1) (divList n 1)))

elemIntList :: Int -> [Int] -> Int
elemIntList 1 (h:t) = h
elemIntList n (h:t) = elemIntList (n-1) t
elemIntList n [] = 0

commonDivList :: [Int] -> [Int] -> [Int]
commonDivList [] (hb:tb) = []
commonDivList (ha:ta) [] = []
commonDivList a@(ha:ta) b@(hb:tb)
	| ha == hb
		= ha : commonDivList ta tb
	| ha > hb
		= commonDivList a tb
	|otherwise
		= commonDivList ta b

divList :: Int -> Int -> [Int]
divList n m
	| n == 0
		= []
	| m > n
		= [n]
	| n `mod` m == 0
		= m : divList n (m+1)
	| otherwise
		= divList n (m+1)

-----------------------------------------------------------------------------------------
     
-- ASKHSH 3

seconds :: (Int,Int)->(Int,Int,Int)->Int

seconds (d,mm) (h,m,s) 
	| mm > 12 || mm < 1 || d < 0 || d > maxday mm || h > 24 || h < 0 || m > 60 || m < 0 || s > 60 || s < 0
		= -1
	| mm > 1
		= (d * (24 * 3600)) + seconds (maxday (mm-1),mm-1) (h,m,s)
	| d > 1
		= ((d-1) * (24 * 3600)) + (h * 3600) + (m * 60) + s
	| d == 1 && mm ==1 
		= (h * 3600) + (m * 60) + s
	|otherwise = -1



maxday :: Int -> Int
maxday 1 = 31
maxday 2 = 28
maxday 3 = 31
maxday 4 = 30
maxday 5 = 31
maxday 6 = 30
maxday 7 = 31
maxday 8 = 31
maxday 9 = 30
maxday 10 = 31
maxday 11 = 30
maxday 12 = 31


-----------------------------------------------------------------------------------------
     
-- ASKHSH 4

sumfab :: (Int->Int->Int->Int)->Int->Int->Int

sumfab f a b 
	| a == b
		= f a a b
	|otherwise
		= f a a b + help f a (a+1) b
		
--
help :: (Int->Int->Int->Int) -> Int -> Int -> Int -> Int
help f a k b
	| k == b
		= f a b b
	|otherwise
		= f a k b + help f a (k+1) b



-----------------------------------------------------------------------------------------
     



