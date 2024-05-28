def decimal_to_binary(str):
    binary = bin(int(str))[2:]
    return binary

ip = input("Enter the IP Address: ")
div = ip.split(".")
print(div)
for item in div:
    print(decimal_to_binary(item))
    print(len(decimal_to_binary(item)))
    print(int(decimal_to_binary(item), 2)) #Binary to decimal convertion
