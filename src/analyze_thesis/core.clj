(ns analyze-thesis.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [pdfboxing.text :as text]))

(defn- all-pdf-files "List all the pdf files in the directory" [directory]
  (filter (fn [x]
            (.contains (.getName x) ".pdf")))
  (file-seq (io/file directory)))

(defn convert-to-text "Convert PDF files to text for creating a corpus" [data-directory text-directory]
  (for [pdf-file (all-pdf-files data-directory)]
    (let [pdf-file-name (replace (.getName pdf-file) #".pdf")
          text-file-name (str pdf-file-name ".txt")
          pdf-content (text/extract (slurp pdf-file))]
      (slurp (str text-directory "/" text-file-name) pdf-content))))

(defn -main
  "Run the data analysis pipeline"
  [& args]
  (convert-to-text "/Users/sushants/code/anayze_thesis/data/pdf" "/Users/sushants/Documents"))


