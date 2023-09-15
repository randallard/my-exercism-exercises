(ns sublist-test
  (:require [clojure.test :refer [deftest is testing]]
            sublist))

(deftest empty-lists-test
    (is (= :equal (sublist/classify [] []))))

(deftest empty-list-within-non-empty-list-test
    (is (= :sublist (sublist/classify [] [1 2 3]))))

(deftest non-empty-list-contains-empty-list-test
    (is (= :superlist (sublist/classify [1 2 3] []))))

(deftest list-equals-itself-test
    (is (= :equal (sublist/classify [1 2 3] [1 2 3]))))

(deftest different-lists
    (is (= :unequal (sublist/classify [1 2 3] [2 3 4]))))

(deftest false-start
    (is (= :sublist (sublist/classify [1 2 5] [0 1 2 3 1 2 5 6]))))

(deftest consecutive
    (is (= :sublist (sublist/classify [1 1 2] [0 1 1 1 2 1 2]))))

(deftest sublist-at-start
    (is (= :sublist (sublist/classify [0 1 2] [0 1 2 3 4 5]))))

(deftest sublist-in-middle
    (is (= :sublist (sublist/classify [2 3 4] [0 1 2 3 4 5]))))

(deftest sublist-at-end
    (is (= :sublist (sublist/classify [3 4 5] [0 1 2 3 4 5]))))

(deftest at-start-of-superlist
    (is (= :superlist (sublist/classify [0 1 2 3 4 5] [0 1 2]))))

(deftest in-middle-of-superlist
    (is (= :superlist (sublist/classify [0 1 2 3 4 5] [2 3]))))

(deftest at-end-of-superlist
    (is (= :superlist (sublist/classify [0 1 2 3 4 5] [3 4 5]))))

(deftest first-list-missing-element-from-second-list
    (is (= :unequal (sublist/classify [1 3] [1 2 3]))))

(deftest second-list-missing-element-from-first-list
    (is (= :unequal (sublist/classify [1 2 3] [1 3]))))

(deftest order-matters-to-a-list
    (is (= :unequal (sublist/classify [1 2 3] [3 2 1]))))

(deftest same-digits-but-different-numbers
    (is (= :unequal (sublist/classify [1 0 1] [10 1]))))

(deftest second-list-continues-first-list
  (is (= :unequal (sublist/classify [1] [2 3]))))

(deftest get-smaller-bigger-and-potential-result-returns-nil-if-same-number-of-elements
    (is (nil? (sublist/get-smaller-bigger-and-potential-result [1] [2]))))

(deftest get-smaller-bigger-and-potential-result-returns-potential-as-sublist
    (is (= :sublist (:potential-result (sublist/get-smaller-bigger-and-potential-result [1] [2 3])))))

(deftest get-smaller-bigger-and-potential-result-returns-potential-as-superlist
    (is (= :superlist (:potential-result (sublist/get-smaller-bigger-and-potential-result [2 3] [1])))))

(deftest get-smaller-bigger-and-potential-result-returns-smaller-list-when-second
    (is (= [1] (:smaller (sublist/get-smaller-bigger-and-potential-result [2 3] [1])))))

(deftest get-smaller-bigger-and-potential-result-returns-smaller-list-when-first
    (is (= [1] (:smaller (sublist/get-smaller-bigger-and-potential-result [1] [2 3])))))

(deftest get-smaller-bigger-and-potential-result-returns-bigger-list-when-second
    (is (= [2 3] (:bigger (sublist/get-smaller-bigger-and-potential-result [1] [2 3])))))

(deftest get-smaller-bigger-and-potential-result-returns-bigger-list-when-first
    (is (= [2 3] (:bigger (sublist/get-smaller-bigger-and-potential-result [2 3] [1])))))

(deftest define-contained-list-returns-unequal
    (is (= :unequal (sublist/define-contained-list [1] [2]))))

(deftest define-contained-list-returns-superlist
    (is (= :superlist (sublist/define-contained-list [1 2] [2]))))

(deftest define-contained-list-returns-sublist
    (is (= :superlist (sublist/define-contained-list [1 2] [2]))))

