#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <netinet/ip.h>
#include <string.h>
#include <stdint.h>

int main(void)
{
    int s;
    struct sockaddr_in server, client;
    s = socket(AF_INET, SOCK_STREAM, 0);

    if (s < 0) {
        perror("Socket server");
        return 1;
    }

    memset(&server, 0, sizeof(server));
    server.sin_port = htons(8889);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;

    if (bind(s, (struct sockaddr *)&server, sizeof(server)) < 0) {
        perror("Bind");
        return 1;
    }

    listen(s, 5);

    int l = sizeof(client);
    memset(&client, 0, sizeof(client));

    while (1) {
        int c = accept(s, (struct sockaddr*)&client, &l);
        if (c < 0) {
            perror("Accept");
            continue;
        }

        printf("Client connected.\n");

        int16_t n;
        if (recv(c, &n, sizeof(n), 0) <= 0) {
            perror("Failed to receive count");
            close(c);
            continue;
        }
        n = ntohs(n);  // Convert count to host byte order

        int16_t suma = 0;

        for (int i = 0; i < n; i++) {
            int16_t a;
            if (recv(c, &a, sizeof(a), 0) <= 0) {
                perror("Failed to receive number");
                break;
            }
            suma += ntohs(a);  // Accumulate the sum in host byte order
        }

        suma = htons(suma);  // Convert sum to network byte order
        send(c, &suma, sizeof(suma), 0);  // Send sum to client

        close(c);  // Close client connection
        printf("Sum sent to client. Connection closed.\n");
    }

    return 0;
}
