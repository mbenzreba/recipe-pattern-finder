
### How /corpora is organized:

#### recipes-raw.txt
These are recipes taken straight from [Recipe Cart](http://www.getrecipecart.com/popular). Currently not used for
anything.

#### peruvian-chicken.txt
The first recipe I saw when browsing GetRecipeCart one day. Used to run `opennlp-basic-pipeline.ps1` against for
a shallow understanding of language patterns common across recipe instructions.

#### recipes.train

These are the most popular recipes of all time from GetRecipeCart. This piece of text should be used as the basis
for training new models. For example, to train a new tokenizer model 