f = imread('cameraman.tif');
[n m] = size(f);
imagesc(f);
colormap(gray);
figure
F = dct2(f);
katofli = 20
miden = 0;
for i = 1:n
  for j = 1:m
    if abs(F(i,j))<katofli
      F(i,j) = 0;
      miden ++;
    endif
  endfor
endfor
Fi = idct2(F);
Fi8 = uint8(Fi);
colormap(gray);
imagesc(Fi8);
Psnr = 10*log10((255*255)/(sum(sum((f-Fi8).^2))/(n*m)))
miden