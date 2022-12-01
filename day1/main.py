from queue import PriorityQueue

f = open("input", "r")

inp = f.readlines()

gr = PriorityQueue()
cur = 0
for n in inp:
    if n == "\n":
        gr.put(-cur)
        cur = 0
        continue

    n = int(n)
    cur += n

r = 0
for _ in range(3):
    r += gr.get()

print(-r)
