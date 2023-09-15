(ns robot-simulator)

(defn robot
  [coordinates bearing] ;; <- arglist goes here
  ;; your code goes here
  {:bearing bearing
   :coordinates coordinates})

(defn get-bearing-after-turn-right-from [bearing]
  (cond (= bearing :north) :east
        (= bearing :east)  :south
        (= bearing :south) :west
        (= bearing :west)  :north
        :else (str "couldn't match bearing" bearing)))

(defn get-bearing-after-turn-left-from [bearing]
  (cond (= bearing :north) :west
        (= bearing :east)  :north
        (= bearing :south) :east
        (= bearing :west)  :south
        :else (str "couldn't match bearing" bearing)))

(defn get-axis [bearing] 
    (cond (= bearing :north) :y
          (= bearing :east)  :x  
          (= bearing :south) :y
          (= bearing :west)  :x
          :else (str "couldn't match bearing" bearing)))

(defn get-movement [bearing] 
    (cond (= bearing :north) +1
        (= bearing :east)    +1
        (= bearing :south)   -1
        (= bearing :west)    -1
        :else (str "couldn't match bearing" bearing)))

(defn get-transform [bearing]
  (let [axis     (get-axis bearing)
        movement (get-movement bearing)]
    { :axis     axis
      :movement movement }))

(defn get-new-position [robot]
  (let [this-transform (get-transform (:bearing robot))
        axis (:axis this-transform)
        other-axis (cond (= :x axis) :y
                         (= :y axis) :x)]
    { axis       (+ (axis (:coordinates robot)) (:movement this-transform)) 
      other-axis (other-axis (:coordinates robot))}))

(defn get-robot-plus-string [this-robot this-string]
  {:coordinates (:coordinates this-robot)
   :bearing (:bearing this-robot)
   :string (str this-string)})

(defn get-robot-minus-string [this-robot]
  {:coordinates (:coordinates this-robot)
   :bearing (:bearing this-robot)})

(defn do-step-for [command this-robot]
  (cond (= command \R) {:coordinates (:coordinates this-robot)
                        :bearing     (get-bearing-after-turn-right-from (:bearing this-robot))}
        (= command \L) {:coordinates (:coordinates this-robot)
                        :bearing     (get-bearing-after-turn-left-from (:bearing this-robot))}
        (= command \A) {:coordinates (get-new-position this-robot)
                        :bearing     (:bearing this-robot)}
        :else (throw (AssertionError. "Wrong input."))))

(defn simulate
  [command this-robot]
  (reduce  (fn [robot command]
    (do-step-for command robot))
      this-robot
      (seq command)))

