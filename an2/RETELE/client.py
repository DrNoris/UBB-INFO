import socket

TCP_IP = "127.0.0.1"
TCP_PORT = 8888

# Get input from user
MESSAGE = str(input("Dati un numar: "))

# Create socket and connect to server
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))

# Send the first number to server
s.send(MESSAGE.encode())  # encode the string before sending
print("Am trimis la server:", MESSAGE)

# Get second input from user
MESSAGE2 = str(input("Dati al doilea numar: "))

# Send the second number to server
s.send(MESSAGE2.encode())  # encode the string before sending
print("Am trimis la server:", MESSAGE2)

# Receive response from the server
data = s.recv(10)
s.close()

# Print the received sum from server
print("Am primit de la server:", data.decode())  # decode the bytes to string
