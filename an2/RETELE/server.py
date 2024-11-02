import socket
import threading

def pasareasca(word):
    #functia returneaza cuvantul in limba pasareasca
    vowels = "aeiouAEIOU"
    result = ""
    
    for char in word:
        if char in vowels:
            result += char + "p" + char 
        else:
            result += char
    
    return result

def handle_client(conn, addr):
    print("Connection established with ", addr)
    
    while True:
        data = conn.recv(1024).decode()  # primeste bufferul si ii da decode (ntoh)
        
        if not data or data == "STOP":
            print(f"\nConnection closed with {addr}")
            break
        
        print(f"Received message from {addr}: {data}")
        
        # transforma cuvantul 
        transformed_word = pasareasca(data)
        print(f"Transformed message for {addr}: {transformed_word}")
        
        # trimite cuvant inapoi la client
        conn.sendall(transformed_word.encode())
        
    conn.close()

TCP_IP = "127.0.0.1"
TCP_PORT = 8888

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))
s.listen(15)  # da listen pentru cel mult 15 clienti
print(f"Server listening on {TCP_IP}:{TCP_PORT}")

while True:
    conn, addr = s.accept()
    
    # pentru fiecare client conectat se face un nou thread
    client_thread = threading.Thread(target=handle_client, args=(conn, addr))
    client_thread.start()
