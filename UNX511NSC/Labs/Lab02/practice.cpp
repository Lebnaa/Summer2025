#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

#define BSIZE 16384

int main(void) {
    int fin, fout;
    char buf[BSIZE];
    int count;

    if ((fin = open("foo", O_RDONLY)) < 0) {
        perror("foo");
        exit(1);
    }

    if ((fout = open("output.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644)) < 0) {
        perror("open");
        exit(1);
    }

    while ((count = read(fin, buf, BSIZE)) > 0) {
        if (write(fout, buf, count) != count) {
            perror("write");
            exit(1);
        }
    }
    close(fin);
    close(fout);

    return 0;
}
