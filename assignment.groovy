// ---------------------------
//
// HOMEWORK
//
// You can use either Groovy or Java.
//
// Design a routine that will calculate the average Product price per Group.
//
// The Price of each Product is calculated as:
// Cost * (1 + Margin)
//
// Assume there can be a large number of products and a large number of categories.
//

// Plus points:
// - use Groovy and its closures
// - make the category look-up performance effective
// - use method Collection.inject
 
// contains information about [Product, Group, Cost]
MAX_DEFAULT = 99999

def products = [
    ["A", "G1", 20.1],
    ["B", "G2", 98.4],
    ["C", "G1", 49.7],
    ["D", "G3", 35.8],
    ["E", "G3", 105.5],
    ["F", "G1", 55.2],
    ["G", "G1", 12.7],
    ["H", "G3", 88.6],
    ["I", "G1", 5.2],
    ["J", "G2", 72.4]]
 

// contains information about Category classification based on product Cost
// [Category, Cost range from (inclusive), Cost range to (exclusive)]
// i.e. if a Product has Cost between 0 and 25, it belongs to category C1
def category = [
    ["C3", 50, 75],
    ["C4", 75, 100],
    ["C2", 25, 50],
    ["C5", 100, null],
    ["C1", 0, 25]]
 
// contains information about margins for each product Category
// [Category, Margin (either percentage or absolute value)]

def margins = [
    "C1" : "20%",
    "C2" : "30%",
    "C3" : "0.4",
    "C4" : "50%",
    "C5" : "0.6"]


 
// ---------------------------
//
// YOUR CODE GOES BELOW THIS LINE
//
// Assign the 'result' variable so the assertion at the end validates
//
// ---------------------------
def catMargin = ''
def catMarginVal = 0.0
def finalProductPrice = 0.0
def groupProductPrice = ["G1":[], "G2":[], "G3":[]]
def avgGroupProductPrice = [:];

category.each{cat->
    
    if(cat[2]==null)
    {
        cat[2] = MAX_DEFAULT;
    }
    products.inject([:]){result,prod->
        if(prod[2]>cat[1] && prod[2]<cat[2])
        {
         catMargin = margins[cat[0]]
         if (catMargin.endsWith("%")){
             catMarginVal = catMargin.minus("%").toInteger() / 100
         }
         else{
             catMarginVal =  catMargin.toFloat()
         }
       finalProductPrice = prod[2].multiply((1.plus(catMarginVal)));
       groupProductPrice[prod[1]].push(finalProductPrice);
        }
        
    }
   

}
groupProductPrice.each{k,v->
    def average =  (v.sum() / v.size()).round(1)
    avgGroupProductPrice.put(k,average)
}

def result = avgGroupProductPrice

// ---------------------------
//
// IF YOUR CODE WORKS, YOU SHOULD GET "It works!" WRITTEN IN THE CONSOLE
//
// ---------------------------
assert result == [
    "G1" : 37.5,
    "G2" : 124.5,
    "G3" : 116.1
    ] : "It doesn't work"
 
println "It works!"
