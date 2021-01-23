server {
        listen       443 default_server;
        listen       [::]:80 default_server;
        server_name  godpaper.cn;
        root         /usr/share/nginx/html;
		ssl on;
		ssl_certificate 1_godpaper.cn_bundle.crt;
		ssl_certificate_key 2_godpaper.cn.key;
		ssl_session_timeout 5m;
		ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
		ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:HIGH:!aNULL:!MD5:!RC4:!DHE;
		ssl_prefer_server_ciphers on;
        #rewrite ^(.*) https://$host$1 permanent;

        # Load configuration files for the default server block.
        include /etc/nginx/default.d/*.conf;

        location / {
		proxy_set_header        Host $host;
		proxy_set_header        X-Real-IP $remote_addr;
		proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
		proxy_set_header        X-Forwarded-Proto $scheme;
		# Fix the “It appears that your reverse proxy set up is broken" error.
		proxy_pass          http://godpaper.cn:81;
		proxy_read_timeout  90;
		proxy_redirect      http://godpaper.cn:81 https://godpaper.cn;
        }

        error_page 404 /404.html;
            location = /40x.html {
        }

        error_page 500 502 503 504 /50x.html;
            location = /50x.html {
        }
    }