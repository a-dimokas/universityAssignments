#1
fin0=fopen("frame0.raw","r");
frame0=fread(fin0,[176,144])';
fin1=fopen("frame1.raw","r");
frame1=fread(fin1,[176,144])';
fclose(fin0);
fclose(fin1);
#2
#frame0 intra mode
[n m] = size(frame0);
  i = 1;
  while i <= n
    j = 1;
    while j <= m
      # dct2d kai kvantisi
      quant(i:i+3, j:j+3) = quantization(
        integer_transform(frame0(i:i+3,j:j+3)),27);
      # antistrofi kvantisi kai antistrofo dct2d
      result(i:i+3, j:j+3) = inv_integer_transform(
        inv_quantization(quant(i:i+3,j:j+3),27));
      j = j+4;
    endwhile
    i = i+4;
  endwhile
  #show entropy of quanted frame0
  entropy(uint8(abs(quant)))
  #post-scaling rounging/64 and psnr show
  final0 = round(result/64);
  Psnr = 10*log10((255*255)/(sum(sum((frame0-final0).^2))/(n*m)))
  #show generated image
  figure;
  imagesc(final0);
  colormap(gray);
#3-4-5
for count = 1:1:2
  N = (4*count);  
  for i = 1:16:n
    for j = 1:16:m
       SADmin = 256 * 16 * 16;
       
       for k = -N:1:N
         for l = -N:1:N
            if(i+k<1)
              k = 1-i;
            elseif(i+k+15>144)
              k = 129-i;
            end
            if(j+l<1)
              l = 1-j;
            elseif(j+l+15>176)
              l = 161-j;
            end
            
           SAD = sum(sum(abs(frame1(i:i+15, j:j+15) - final0(i+k:i+k+15, j+l:j+l+15))));
           if (SAD < SADmin)
             SADmin = SAD;
             dy = k;
             dx = l;
            end
          end
        end
        #disregard out of bound values
        checki = i+dy;
        checkj = j+dx;
        if(checki<1)
          checki = 1;
        elseif(checki+15>144)
          checki = 129;
        end
        if(checkj<1)
          checkj = 1;
        elseif(checkj+15>176)
          checkj = 161;
        end
        #calculate new frame by apllying motion vectors in otiginal frame
        frameP(i:i+15,j:j+15) = final0(checki:checki+15,checkj:checkj+15);
        frameError(i:i+15,j:j+15) = frame1(i:i+15,j:j+15) - frameP(i:i+15,j:j+15);
     end
  end
  #same as 2 but for 5  
  i = 1;
  while i <= n
    j = 1;
    while j <= m
      # dct2d kai kvantisi
      quant(i:i+3, j:j+3) = quantization(
        integer_transform(frameError(i:i+3,j:j+3)),27);
      # antistrofi kvantisi kai antistrofo dct2d
      result(i:i+3, j:j+3) = inv_integer_transform(
        inv_quantization(quant(i:i+3,j:j+3),27));
      j = j+4;
    endwhile
    i = i+4;
  endwhile
  
  #post-scaling rounging/64
  finalError = round(result/64);
  
  
  #calculate new frame
  frame1new = frameP +finalError;
  PsnrError = 10*log10((255*255)/(sum(sum((frame1-frame1new).^2))/(n*m)))
  #show entropy of quanted new frame
  entropy(uint8(abs(quant)))
  #show generated image
  figure;
  imagesc(frame1new);
  colormap(gray);
  
    
    
end    
