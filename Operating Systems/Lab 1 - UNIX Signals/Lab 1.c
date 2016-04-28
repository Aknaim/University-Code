# include <stdio.h>
# include <signal.h>
# include <unistd.h>
# include <stdlib.h>

// user - defined signal handler for alarm .

void sig_handler( int signo ) {
	if ( signo == SIGALRM ) {
		printf(" ALARM\n");
	} else if ( signo == SIGINT ) {
		printf(" CTRL+C pressed!\n");
	} else if ( signo == SIGTSTP ) {
		printf(" CTRL+Z pressed!\n");
		exit(1);
	}
}

int main ( void ) {

		if (signal(SIGALRM, sig_handler) == SIG_ERR ) {
			printf (" failed to register handler .");
			exit(1);
		}
		if(signal(SIGINT, sig_handler) == SIG_ERR ) {
			printf (" failed to register handler .");
			exit(1);
		}
		if (signal(SIGTSTP, sig_handler) == SIG_ERR ) {
			printf (" failed to register handler .");
			exit(1);
		}

	while(1) {
		// printf("test\n");
		// register the signal handler
		//raise(SIGALRM);
		//alarm(2);
		raise(SIGALRM);
		sleep(2);
	}
}