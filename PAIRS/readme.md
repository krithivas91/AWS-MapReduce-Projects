Algorithm

Input: 100KWikiText.txt - A set of 100,000 Wikipedia Documents 
Output: Top 100 Word Pairs according to relative frequency


1) Mapper function created maps the input file. Split the words and store all the keyword pairs in the key. The value of each word pair is set to 1. The word pairs and their initial frequency  are send to the reducer.

2) Reducer function is created to store the mapper output. For loop is initiated to parse the document and calculates the sum (count) of the frequency of each word pair.

3) Use a Hashmap to store the WordPair and its total count.

4) Cleanup function is created to sort the word pairs according to their frequency in ascending order.

5) Create a counter to display the top 100 WordPairs.
