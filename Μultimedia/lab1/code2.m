f = imread('cameraman.tif');
entropy(f)
figure;
imagesc(f);
colormap(gray);
[n m] = size(f);

Q1 = [16 11 10 16 24 40 51 61; 
  12 12 14 19 26 58 60 55;
  14 13 16 24 40 57 69 56;
  14 17 22 29 51 87 80 62;
  18 22 37 56 68 109 103 77;
  24 35 55 64 81 104 113 92;
  49 64 78 87 103 121 120 101;
  72 92 95 98 112 100 103 99];
  
Q2 = 3*Q1;
Q3 = 4*Q1;

x = 1;
while x <= 3
  if x==1
    Q=Q1;
  elseif x==2
    Q=Q2;
  else
    Q=Q3;
  endif


  i = 1;
  while i <= n
    j = 1;
    while j <= m
      Fk(i:i+7, j:j+7) = round(dct2(f(i:i+7,j:j+7))./Q);
      j = j+8;
    endwhile
    i = i+8;
  endwhile

  entropy(uint8(abs(Fk)))

  i = 1;
  while i <= n
    j = 1;
    while j <= m
      Fki(i:i+7,j:j+7)=uint8(idct2(Fk(i:i+7,j:j+7).*Q));
      j = j+8;
    endwhile
    i = i+8;
  endwhile

  f = double(f);
  Fki = double(Fki);
  Psnr = 10*log10((255*255)/(sum(sum((f-Fki).^2))/(n*m)))
  figure;
  imagesc(Fki);
  colormap(gray);
  x = x+1;
end
