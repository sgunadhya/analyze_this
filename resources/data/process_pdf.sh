for file in $(ls -a pdf/*.pdf); do
  file_name=`echo $file | sed 's/[^0-9]*//g'`
  echo $file_name
  $(pdftotext $file text/$file_name.txt)
done
