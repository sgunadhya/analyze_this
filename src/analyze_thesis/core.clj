(ns analyze-thesis.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [pantomime.extract :as extract]))

(defn is-pdf-file "Check if the file passed is a PDF file " [file-object]
  (and (.contains (.getName file-object) ".pdf") (not (.isDirectory file-object))))

(defn all-pdf-files "List all the PDF files in the directory" [directory]
  (filter is-pdf-file (file-seq (io/file directory))))

(defn convert-to-text "Convert PDF files to text for creating a corpus" [data-directory text-directory]
  (doseq [pdf-file (all-pdf-files data-directory)]
    (let [pdf-file-name (clojure.string/replace (.getName pdf-file) ".pdf" "")
          text-file-name (str pdf-file-name ".txt")
          pdf-content (:text (extract/parse (.getPath pdf-file)))]
      (println pdf-content))))

(defn -main
  "Run the data analysis pipeline"
  [& args]
  (convert-to-text "/Users/sushants/code/analyze_thesis/resources/data/pdf" "/Users/sushants/Documents"))


