#!/usr/bin/env python3
import json
import sys
import xml.etree.ElementTree as ET

# Path to mutations.xml
xml_file = sys.argv[1]
tree = ET.parse(xml_file)
root = tree.getroot()

mutations = root.findall('.//mutation')
total = len(mutations)
killed = sum(1 for m in mutations if m.attrib.get('detected') == 'true')

score = round((killed / total) * 100) if total > 0 else 0

# Build JSON for Shields.io
badge_json = {
    "schemaVersion": 1,
    "label": "mutation",
    "message": f"{score}%",
    "color": "green" if score >= 80 else "yellow" if score >= 50 else "red"
}

with open('mutation-score.json', 'w') as f:
    json.dump(badge_json, f)
# print(f"Mutation score: {score}% -> mutation-score.json")
print(score)
