(ns analyze-thesis.core
  (:gen-class)
  (:require [clojure.java.io :as io]))

(defn all-pdf-files "List all the pdf files in the directory" [directory]
  (filter (fn [x]
            (.contains (.getName x) ".pdf")))
  (file-seq (io/file directory)))


(defn -main
  "Run the data analysis pipeline"
  [& args]
  (println (all-pdf-files "/Users/sushants/code/analyze_thesis/data/pdf")))


