#!/usr/bin/env python3
import json
import sys
import xml.etree.ElementTree as ET

# Path to mutations.xml
xml_file = sys.argv[1]
output_file = sys.argv[2]
tree = ET.parse(xml_file)
root = tree.getroot()

mutations = root.findall('.//mutation')
total = len(mutations)
killed = sum(1 for m in mutations if m.attrib.get('detected') == 'true')

score = round((killed / total) * 100) if total > 0 else 0

# Build JSON for Shields.io
badge_json = {
    "schemaVersion": 1,
    "label": "Mutation testing",
    "message": f"{score}%",
    "color": "green" if score >= 80 else "yellow" if score >= 50 else "red",
    "logoSvg": "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<svg\n   xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\n   xmlns:cc=\"http://creativecommons.org/ns#\"\n   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n   xmlns:svg=\"http://www.w3.org/2000/svg\"\n   xmlns=\"http://www.w3.org/2000/svg\"\n   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\n   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\n   version=\"1.1\"\n   width=\"226.16472\"\n   height=\"187.2227\"\n   id=\"svg3004\"\n   xml:space=\"preserve\"\n   sodipodi:docname=\"pitest.svg\"\n   inkscape:version=\"1.0.2 (e86c8708, 2021-01-15)\"><sodipodi:namedview\n     id=\"namedview832\"\n     pagecolor=\"#ffffff\"\n     bordercolor=\"#666666\"\n     borderopacity=\"1.0\"\n     inkscape:pageshadow=\"2\"\n     inkscape:pageopacity=\"0.0\"\n     inkscape:pagecheckerboard=\"false\"\n     showgrid=\"false\"\n     fit-margin-top=\"0\"\n     fit-margin-left=\"0\"\n     fit-margin-right=\"0\"\n     fit-margin-bottom=\"0\"\n     inkscape:zoom=\"0.79588012\"\n     inkscape:cx=\"143.23765\"\n     inkscape:cy=\"69.73412\"\n     inkscape:window-width=\"1680\"\n     inkscape:window-height=\"912\"\n     inkscape:window-x=\"0\"\n     inkscape:window-y=\"25\"\n     inkscape:window-maximized=\"1\"\n     inkscape:current-layer=\"svg3004\"\n     inkscape:document-rotation=\"0\" /><metadata\n     id=\"metadata3010\"><rdf:RDF><cc:Work\n         rdf:about=\"\"><dc:format>image/svg+xml</dc:format><dc:type\n           rdf:resource=\"http://purl.org/dc/dcmitype/StillImage\" /><dc:title></dc:title></cc:Work></rdf:RDF></metadata><defs\n     id=\"defs3008\"><clipPath\n       id=\"clipPath3020\"><path\n         d=\"M 0,4824.86 V 0 h 4747.99 v 4824.86 z\"\n         id=\"path3022\" /></clipPath></defs><g\n     id=\"g3016\"\n     transform=\"matrix(0.04763378,0,0,-0.04763378,0,229.82632)\"\n     style=\"stroke-width:1.0288;stroke-miterlimit:4;stroke-dasharray:none\"><g\n       clip-path=\"url(#clipPath3020)\"\n       id=\"g3018\"\n       style=\"stroke-width:1.0288;stroke-miterlimit:4;stroke-dasharray:none\"><path\n         d=\"m 3085.76,2240.16 c -56.1,-83.53 -122.19,-160.29 -194.12,-230.97 -72.53,-70.15 -152.43,-132.7 -237.31,-187.46 -170.37,-108.86 -363.29,-181.38 -562.03,-213.28 -198.09,-32.26 -403.06,-23.61 -594.14,29.26 196.63,-24.41 394.12,-17.07 584.45,23.85 189.81,40.55 371.61,114.89 534.6,218.52 81.91,51.27 158.47,110.93 229.53,176.57 71.58,65.19 136.11,137.92 195.33,214.89 117.55,154.85 207.95,330.84 269.36,519.15 -33.55,-195.45 -112.42,-384.51 -225.67,-550.53 z m 1215.7,1662.14 h 446.53 c -27.53,73.47 -93.09,128.34 -172.7,140.68 6.17,7.72 10.02,17.37 10.02,27.97 v 189.79 c 0,24.85 -20.33,45.18 -45.17,45.18 h -74.57 c -24.86,0 -45.18,-20.33 -45.18,-45.18 v -28.95 h -64.66 c -132.95,346.42 -468.88,593.07 -861.53,593.07 -488.26,0 -888.92,-381.33 -920.35,-861.79 l 1109.99,-60.78 -897.47,-49.15 C 2680.94,3288.8 2376.68,2804.65 1948.46,2454.39 L 572.605,1491.03 3.64453,1092.63 0,1090.07 31.9727,1044.41 C 143.398,885.289 362.715,846.621 521.836,958.039 L 1015.03,1303.4 c 291.47,-214.99 651.65,-342.15 1041.6,-342.15 970.25,0 1756.82,786.56 1756.82,1756.82 0,72.19 -4.46,144.37 -13.26,216.01 -7.86,63.85 -19.43,127.38 -11.84,191.92 9.78,83.15 49.74,175.26 141.12,191.09 0.02,0.11 0,0.22 0.02,0.33 l 389.42,67.69 c 0.24,-0.14 0.48,-0.25 0.76,-0.41 -0.2,0.14 -0.39,0.3 -0.6,0.44 l 0.36,0.06 -2.11,1.22 c -5.53,3.83 -10.98,7.79 -16.28,11.91 -5.22,4.03 -10.3,8.21 -15.29,12.55 -29.71,25.78 -55.12,56.41 -74.99,90.63 -0.18,0.29 -0.35,0.6 -0.54,0.9 -3.09,5.38 -6.05,10.84 -8.87,16.39 -0.19,0.35 -0.35,0.69 -0.54,1.04 -14.8,29.32 -25.68,60.94 -31.94,94.21 -0.12,0.61 -0.22,1.21 -0.33,1.81 -1.08,5.95 -2.04,11.95 -2.82,18 -0.11,0.83 -0.21,1.68 -0.31,2.52 -0.74,6 -1.33,12.05 -1.78,18.14 -0.05,0.73 -0.11,1.43 -0.16,2.16 -0.44,6.72 -0.72,13.49 -0.79,20.31 -0.01,1.18 -0.09,2.33 -0.09,3.51 0,8.09 0.36,16.09 0.91,24.04 0.11,1.89 0.26,3.77 0.42,5.65 0.63,7.73 1.44,15.41 2.56,22.99 0.02,0.11 0.02,0.23 0.05,0.35 1.22,8.16 2.77,16.21 4.52,24.2 0.24,1.03 0.49,2.07 0.73,3.12 1.77,7.75 3.77,15.4 6.09,23.13 l -1.72,1.68 c 6.45,37.94 9.98,76.87 9.98,116.64 h 115.33\"\n         id=\"path3044\"\n         style=\"fill:#FE0183;fill-opacity:1;fill-rule:nonzero;stroke:none;stroke-width:1.0288;stroke-miterlimit:4;stroke-dasharray:none\" /></g></g></svg>"
}

with open(output_file, 'w') as f:
    json.dump(badge_json, f)
