# FileCopyDeleter
Deletes duplicate files within a directory

Runs from command line. Following is the format:

java -jar name_of_jar "path of root folder" command.

Commands are as follows: 
deleteSub: Delete duplicate files in the root's subdirectory
subdirectory: List files in the subdirectory, if able to
deleteAll: Delete's all duplicate files beginning from the root, branching to all other directories beneath;
                      If a file is in directory A and in directory B, they will not be deleted from both.
