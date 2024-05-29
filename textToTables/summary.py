import numpy as np


class LinguisticSummaries:

    def __init__(self, text, degreeOfTruth):
        self.text = text
        self.degreeOfTruth = degreeOfTruth

    def getDegreeOfTruth(self):
        return self.degreeOfTruth

    def __str__(self):
        return f"{self.text}  [{self.degreeOfTruth}]"

    def printToTable(self, best):
        return f"{self.text} & {np.round(self.degreeOfTruth, 3)} & {np.round(np.abs(best - self.degreeOfTruth), 
                                                                             3)} \\\\ \\hline"
