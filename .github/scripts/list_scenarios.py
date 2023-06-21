from gherkin.parser import Parser
import os
import json

parser = Parser()
path = 'src/test/resources/features/'
files = os.listdir(path)
output = []
for file in files:
    with open(f'{path}{file}', 'r') as fh:
        data = fh.read()
        gherkin_document = parser.parse(data)
        names = [{"file": file, "name": x["scenario"]["name"]} for x in gherkin_document["feature"]["children"]]
        output.extend(names)

with open(os.environ['GITHUB_OUTPUT'], 'a') as go:
    print(f'scenarios={json.dumps(output)}', file=go)


