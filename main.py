def nextFreeSpace(board):
    for i in range(9):
        for j in range(9):
            if board[i][j] == 0:
                return i, j
    return None, None


def isValid(board, row, column, guess):
    if board[row][column] > 0:
        return False
    if guess in board[row]:
        return False
    for i in range(9):
        if board[i][column] == guess:
            return False
    row_start = (row // 3) * 3
    column_start = (column // 3) * 3
    for i in range(row_start, row_start + 3):
        for j in range(column_start, column_start + 3):
            if board[i][j] == guess:
                return False
    return True


def sudokuSolver(board):
    row, column = nextFreeSpace(board)
    if row == None:
        return True
    for guess in range(1, 10):
        if isValid(board, row, column, guess):
            board[row][column] = guess
            displayBoard(board)
            if sudokuSolver(board):
                return True
        board[row][column] = 0
    return False


def displayBoard(board):
    print("==========================")
    for i in range(9):
        for j in range(9):
            print(board[i][j], end=" ")
            if (j + 1) % 3 == 0:
                print("|", end=" ")
        print()
        if (i + 1) % 3 == 0:
            for _ in range(12):
                print("- ", end="")
            print()
    print("==========================")


board = [
    [7, 8, 0, 4, 0, 0, 1, 2, 0],
    [6, 0, 0, 0, 7, 5, 0, 0, 9],
    [0, 0, 0, 6, 0, 1, 0, 7, 8],
    [0, 0, 7, 0, 4, 0, 2, 6, 0],
    [0, 0, 1, 0, 5, 0, 9, 3, 0],
    [9, 0, 4, 0, 6, 0, 0, 0, 5],
    [0, 7, 0, 3, 0, 0, 0, 1, 2],
    [1, 2, 0, 0, 0, 7, 4, 0, 0],
    [0, 4, 9, 2, 0, 6, 0, 0, 7],
]

print("Solving the sudoku Board:")
displayBoard(board)
print("Checking every possible combination:")
if sudokuSolver(board):
    print("Solved! Here's the solution: ")
    displayBoard(board)
else:
    print("Solution Doesn't exist.")
