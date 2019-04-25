import sys

print("== Python project list manager ==")
#the build info file
projectInfo = open(sys.argv[1], "r").readLine()
projectName = projectInfo.split(";")[0]
#the webiste projectlist file
listFile = open(sys.argv[2], "r")

textList = []
found = False

print("[PLM] Reading list...")
# line by line
for x in listFile:
	if x.startswith(projectName):
		# project already mapped, replace with new info
		textList.append(projectInfo)
		found = True
		print("[PLM] Found project mapping, updated it")
	else:
		# other project, just append it
		textList.append(x)

# project is not mapped yet
if not found:
	textList.append(projectInfo)
	print("[PLM] Added project mapping")
		
listFile.close()

#write out new list
with open(sys.argv[2], "w") as listFile:
	for project in textList:
		listFile.write("%s\n" % project)
