def calculated_CRC(data , poly):
    data_len = len(data)
    poly_len = len(poly)
    first = data[0:poly_len]
    rem = bin(int(first , 2) ^ int(poly , 2))[2:]
    i = poly_len
    while i < data_len:
        if len(rem) != len(poly):
            rem = str(rem) + data[i]
            i = i + 1
        else:
            rem = str(bin(int(rem , 2) ^ int(poly , 2))[2:])
    return rem



msg = input("Enter the Message")
poly = input("Enter the Polynomial")

print("received CRC: 100")
print("calculated CRC: " + calculated_CRC(msg , poly))

#Sample input: 100110010
#Sample Polynomial: 1101

