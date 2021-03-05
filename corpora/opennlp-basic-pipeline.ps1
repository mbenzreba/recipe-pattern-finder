
<#  
FILE        : opennlp-basic-pipeline.ps1
PROJECT     : recipe-pattern-finder
DEVELOPER   : Mohamed Benzreba
DESCRIPTION : Runs corpora through a basic pipeline to be split into seperate txt files for
    later analysis. Simply change the paths contained in the Input and Output variables to
    process different sets of corpora using the pipeline. All input and output txt files MUST
    exist before the script is run.
#>



<#  
RUN-FROM:
This script is meant to be run from the recipe-pattern-finder top directory 
#>



# Step 1. Split txt file to sentence for each line
$SentDetectModel = ".\models\en-sent.bin"
$SentDetectInput = ".\corpora\peruvian-chicken.txt"
$SentDetectOutput = ".\corpora\peruvian-chicken-sentdetected.txt"

Clear-Content $SentDetectOutput
Get-Content $SentDetectInput | opennlp SentenceDetector $SentDetectModel > $SentDetectOutput


# Step 2. Split sentences into possible tokens by whitespace.
$TokenizationModel = ".\models\en-token.bin"
$TokenizationOutput = ".\corpora\peruvian-chicken-tokenized.txt"

Clear-Content $TokenizationOutput
Get-Content $SentDetectOutput | opennlp TokenizerME $TokenizationModel > $TokenizationOutput


# Step 3a. Find POSs (Phrases Of Speech) of the input
$POSTaggerModel = ".\models\en-pos-maxent.bin"
$POSTaggerOutput = ".\corpora\peruvian-chicken-postagged.txt"

Clear-Content $POSTaggerOutput
Get-Content $TokenizationOutput | opennlp POSTagger $POSTaggerModel > $POSTaggerOutput


# Step 3-alternate. Parse sentence
$ParserModel = ".\models\en-parser-chunking.bin"
$ParserOutput = ".\corpora\peruvian-chicken-parsed.txt"

Clear-Content $ParserOutput
Get-Content $TokenizationOutput | opennlp Parser $ParserModel > $ParserOutput