print('\n'.join(sorted([
    '10 11 13 15 6 7 8 9 10',
    '10 12 9 10',
    '10 12 9 13 14',
    '10 12 9 13 15 6 16',
    '10 12 9 13 15 6 7 8',
    '11 13 15 6 7 8 9 10 11',
    '11 13 15 6 7 8 9 10 12',
    '1 2',
    '12 9 10 11 13 14',
    '12 9 10 11 13 15 6 16',
    '12 9 10 11 13 15 6 7 8',
    '12 9 10 12',
    '13 15 6 7 8 9 10 11 13',
    '13 15 6 7 8 9 13',
    '1 3 4',
    '1 3 5 6 16',
    '1 3 5 6 7 15',
    '1 3 5 6 7 8 9 10 11 13 14',
    '1 3 5 6 7 8 9 10 11 13 15',
    '1 3 5 6 7 8 9 10 12',
    '1 3 5 6 7 8 9 13 14',
    '1 3 5 6 7 8 9 13 15',
    '15 6 7 15',
    '15 6 7 8 9 10 11 13 14',
    '15 6 7 8 9 10 11 13 15',
    '15 6 7 8 9 13 14',
    '15 6 7 8 9 13 15',
    '6 7 15 6',
    '6 7 8 9 10 11 13 15 6',
    '6 7 8 9 13 15 6',
    '7 15 6 16',
    '7 15 6 7',
    '7 8 9 10 11 13 15 6 16',
    '7 8 9 10 11 13 15 6 7',
    '7 8 9 13 15 6 16',
    '7 8 9 13 15 6 7',
    '8 9 10 11 13 15 6 7 8',
    '8 9 13 15 6 7 8',
    '9 10 11 13 15 6 7 8 9',
    '9 10 12 9',
    '9 13 15 6 7 8 9'
], key=lambda x: int(x.split(' ')[0]))))
