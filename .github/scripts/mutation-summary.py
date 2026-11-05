"""Writes the score and the mutants left of a PIT report as markdown, for the job summary of a workflow run.

Usage: mutation-summary.py <mutations.xml>

The score is rounded as PIT rounds the score it checks against mutationThreshold (see
mutation-score-badge.py). Every mutant that the tests did not detect is listed with its status, class, method,
line and mutator, so that a run shows what is left without its report being downloaded (#59).
"""

import math
import sys
import xml.etree.ElementTree as ElementTree


def summary_of(report):
    mutations = ElementTree.parse(report).getroot().findall("mutation")
    if not mutations:
        raise SystemExit(f"{report} holds no mutation, so it gives no score")
    detected = [mutation for mutation in mutations if mutation.get("detected") == "true"]
    score = math.floor(100 * len(detected) / len(mutations) + 0.5)
    if len(detected) < len(mutations):
        score = min(score, 99)
    lines = [
        "## Mutation testing",
        "",
        f"{len(detected)} of {len(mutations)} mutants detected: **{score}%**",
        "",
    ]
    left = [mutation for mutation in mutations if mutation.get("detected") != "true"]
    if left:
        lines += ["| status | class | method | line | mutator |", "|---|---|---|---|---|"]
        for mutation in left:
            lines.append("| {} | `{}` | `{}` | {} | {} |".format(
                mutation.get("status"),
                mutation.findtext("mutatedClass").rsplit(".", 1)[-1],
                mutation.findtext("mutatedMethod"),
                mutation.findtext("lineNumber"),
                mutation.findtext("description").replace("|", "\\|")))
    return "\n".join(lines) + "\n"


def main(arguments):
    if len(arguments) != 1:
        raise SystemExit(__doc__)
    sys.stdout.write(summary_of(arguments[0]))


if __name__ == "__main__":
    main(sys.argv[1:])
