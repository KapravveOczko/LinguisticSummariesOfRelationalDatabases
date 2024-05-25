import re


def extractData(line):
    pattern = r"^(.*?)\[(.*?)\]$"
    matches = re.match(pattern, line)
    text = matches.group(1).strip()
    degree_of_truth = float(matches.group(2))
    return text, degree_of_truth
