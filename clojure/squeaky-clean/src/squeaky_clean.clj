(ns squeaky-clean
  (:require [clojure.string :as str]))

; replace any spaces with underscores - including any leading and trailing spaces
(defn replace-spaces [s]
  (str/replace s \space \_)
  )
(comment
  (let [s " A"]
    (str/replace s \space \_))
  (replace-spaces " A")
  (replace-spaces  " *  Ab c de _ ")
  (clojure.test/run-tests 'squeaky-clean-test)
  )

(defn remove-non-letters [s]
  (str/replace s #"[^A-Za-zΑ-Ω_]" ""))
(comment
  (str/replace "MyΟβιεγτFinder" #"[^A-Za-zΑ-Ω_]" "")
  (str/replace "MyΟβιεγτΑΩFinder" #"[^A-Za-zΑ-Ω_]" "")
  (str/replace "a1_b2_" #"[^A-Za-z_]" "")
  (remove-non-letters "1My2Finder3")
  (clojure.test/run-tests 'squeaky-clean-test)
  )
(defn clean
  "returns a new string, based on the parameter after the following transformations
      replace all spaces with underscores, including leading and trailing spaces
      remove all characters that are not letters or greek upper-case letters
      "
  [s]
  (-> s replace-spaces
        remove-non-letters))

(comment
  (clean "1My2Finder3")
  (clean "  1My2 Finder 3")
  (clojure.test/run-tests 'squeaky-clean-test)
  )