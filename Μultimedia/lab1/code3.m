input = [7, 0, 4, 0, 2, 3, 0, 0, 2, 4, 0, 1, 0, 0, 0, 1, 0, 0, -1, -1, 0, 1, 0, 0, 0, 1, 0, 0, 2, 1, 0, 0];
[b,a] = size(input);
yolo = 0;
new = 1;
for i = 1:a
  if input(i)==0
    yolo++;
    endif
  if abs(input(i))>0
    level(new)=input(i);
    run(new)=yolo;
    yolo = 0;
    new ++;
  endif
endfor

m = size(level,2);
for i=1:m
  disp([ '(' num2str(level(i)) ',' num2str(run(i)) ') ']) ;
end
