d = {'F':'0','B':'1','L':'0','R':'1'}
lineToNumber = lambda line: int( ''.join( [d[x] for x in line]), 2)
seats = [ lineToNumber(line[:-1]) for line in open('day05.txt','r').readlines()]
low,high = min(seats),max(seats)
print( high, [seat for seat in range(low,high) if seat not in seats][0])
