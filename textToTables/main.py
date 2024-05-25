from localLib import *
from summary import LinguisticSummaries

with open('summaries.txt', 'r') as file:
    lines = file.read().splitlines()

records = lines
summaries = []

for record in records:
    text, degree = extractData(record)
    summaries.append(LinguisticSummaries(text, degree))

max_summary = max(summaries, key=lambda x: x.getDegreeOfTruth())

print(max_summary)

best = max_summary.degreeOfTruth

for summary in summaries:
    print(summary.printToTable(best))
