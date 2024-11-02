#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string.h>
#include <arpa/inet.h>

int main(void)
{
    int c;
    struct sockaddr_in server;

    //socket
    c = socket(AF_INET, SOCK_STREAM, 0);
    if (c < 0) {
        perror("Socket creation failed");
        return 1;
    }

    memset(&server, 0, sizeof(server));
    server.sin_port = htons(8888);
    server.sin_family = AF_INET;

    //ip
    if (inet_pton(AF_INET, "127.0.0.1", &server.sin_addr) <= 0) {
        perror("Invalid server IP");
        return 1;
    }

    //connect
    if (connect(c, (struct sockaddr*) &server, sizeof(server)) < 0) {
        perror("Connection to server failed");
        return 1;
    }

    char buffer[1024];
    printf("Enter text (\"STOP\" will end the session)");


    while(1) {
        //citire de la tastatura in client
        printf("\nString: ");
        fgets(buffer, 1024, stdin);
        buffer[strcspn(buffer, "\n")] = 0;

        //verificare stop
        if (strcmp(buffer, "STOP") == 0) {
            printf("\nThe End");
            send(c, buffer, strlen(buffer), 0);
            break;
        }
        //send and recv
        send(c, buffer, strlen(buffer), 0);
        recv(c, buffer, 1024, 0);
        printf("Transformed word from server: %s\n", buffer);
    }

    close(c);

    return 0;
}
