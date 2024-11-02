#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string.h>
#include <arpa/inet.h>  // Include for inet_pton
#include <stdint.h>

int main(void)
{
    int c;
    struct sockaddr_in server;

    c = socket(AF_INET, SOCK_STREAM, 0);
    if (c < 0) {
        perror("Socket creation failed");
        return 1;
    }

    memset(&server, 0, sizeof(server));
    server.sin_port = htons(8889);
    server.sin_family = AF_INET;

    if (inet_pton(AF_INET, "127.0.0.1", &server.sin_addr) <= 0) {
        perror("Invalid server IP");
        return 1;
    }

    if (connect(c, (struct sockaddr*) &server, sizeof(server)) < 0) {
        perror("Connection to server failed");
        return 1;
    }

    int n;
    printf("Enter the number of integers to send: ");
    scanf("%d", &n);

    int16_t num_to_send = htons(n);
    send(c, &num_to_send, sizeof(num_to_send), 0);  // Send the count of numbers

    printf("Enter %d numbers:\n", n);
    for (int i = 0; i < n; ++i) {
        int16_t a;
        scanf("%hd", &a);
        a = htons(a);
        send(c, &a, sizeof(a), 0);  // Send each number in network byte order
    }

    int16_t suma;
    recv(c, &suma, sizeof(suma), 0);  // Receive the sum
    suma = ntohs(suma);  // Convert sum to host byte order

    printf("\nThe sum from the server is: %hd\n", suma);

    close(c);  // Close the client socket
    return 0;
}
