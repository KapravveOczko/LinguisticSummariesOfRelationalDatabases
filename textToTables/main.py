from localLib import *
from summary import LinguisticSummaries
import numpy as np

with open('summaries.txt', 'r') as file:
    lines = file.read().splitlines()

records = lines
max_summary = 0.0
best = 0.0
summaries_truth_sum = 0.0
best_summaries = []
summaries = []

for record in records:
    text, degree = extractData(record)
    summaries.append(LinguisticSummaries(text, degree))

all_summaries = summaries.copy()

for i in range(5):
    max_summary = max(summaries, key=lambda x: x.getDegreeOfTruth())
    if i == 0:
        best = max_summary.degreeOfTruth
    summaries.remove(max_summary)
    best_summaries.append(max_summary)

for summary in best_summaries:
    print(summary.printToTable(best))
print("-----------------------------------------------------------------------------")
for summary in all_summaries:
    summaries_truth_sum += summary.degreeOfTruth
    print(summary.printToTable(best))
print("-----------------------------------------------------------------------------")
print("mean for all degrees for all summarizers -> ", np.round(summaries_truth_sum/len(all_summaries), 3))
