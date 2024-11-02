import socket
import threading

TCP_IP = "172.30.241.210"
TCP_PORT = 8888

def handle_clinet(conn, addr):
    print ('Connection address:', addr)
    try:
        data = conn.recv(10).decode()
        print ("Am primit de la client", data, " ", addr)
        if not data: return

        data1 = conn.recv(10).decode()
        print ("Am primit de la client", data1, " ", addr)
        if not data1: return

        rezultat = int(data)+int(data1)
        print( "Am trimis la client", rezultat, " ", addr)
        conn.send(str(rezultat).encode())  # echo
    except Exception as e:
        print(f"Error handling client {addr}: {e}")
    finally:
        conn.close()


def startserver():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((TCP_IP, TCP_PORT))
    s.listen(5)

    while 1:
        conn, addr = s.accept()
        client_thread = threading.Thread(target=handle_clinet, args=(conn, addr))
        client_thread.start()

if __name__ == "__main__":
	startserver()