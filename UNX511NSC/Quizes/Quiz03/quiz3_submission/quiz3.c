#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int fd1, fd2, fd3;
    const char file[]="Quiz3.txt";

    fd1 = open(file, O_RDWR | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);
    if (fd1 == -1) { perror("open fd1"); exit(EXIT_FAILURE); }

    fd2 = dup(fd1);
    if (fd2 == -1) { perror("dup"); close(fd1); exit(EXIT_FAILURE); }

    fd3 = open(file, O_RDWR);
    if (fd3 == -1) { perror("open fd3"); close(fd1); close(fd2); exit(EXIT_FAILURE); }

    if (write(fd1, "Hello,", 6) == -1) { perror("write fd1"); }
    if (write(fd2, "world", 5) == -1) { perror("write fd2"); }

    if (lseek(fd2, 0, SEEK_SET) == -1) { perror("lseek fd2"); }

    if (write(fd1, "HELLO,", 6) == -1) { perror("write fd1 second"); }
    if (write(fd3, "Gidday", 6) == -1) { perror("write fd3"); }

    close(fd1);
    close(fd2);
    close(fd3);

    return 0;
}
