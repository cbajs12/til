## vagrant basic
- 가상환경을 만드는 툴
- 가상환경을 만들때 자동화 설치를 지원

### install

1) vagrant와 virtualbox를 설치

2) vagrant init : Vagrantfile 을 만든다.

### Box
- irtual machine 의 Base Image 이다. 여러 프로젝트에서 box 를 공유해서 사용할수 있다.

### command
- vagrant up : 현재 Vagrantfile 환경을 부팅한다.
- vagrant ssh : /vagrant 폴더와 Vagrantfile 이있는 폴더가 sync 가 된다. 파일을 삭제하면 Host 에서도 적용이된다.
- vagrant destroy : up 에 적용됬던 내용들을 모두 지운다. box 를 지우는건 아니다.

### Synced Folders
- 기본적으로 호스트의 Vagrantfile 이 있는 폴더와 vagrant 의 /vagrant 폴더를 공유한다.

### Provision
- 게스트 머신을 반복적으로 생성하고 사용하기 위해 프로비져닝으로 만들어서 사용한다. 내부적으로 지원하는 자동 프로비져닝을 통해 자동적으로 소프트웨어를(nginx mysql 등등) 을 설치한다.

### Networking
- Port Forwarding : Guest machine 에 포트와 Host macine 의 포트를 연결해준다.

### Providers
- provider 는 기본으로는 virutalbox 지만 aws나 VMware로 바꿀수도 있다.

### Example of Vagrantfile
```shell
# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/xenial64"

  config.vm.provider "virtualbox" do |v|
    v.cpus = 1
    v.memory = 256
  end

  config.vm.provision "shell", path: "provisioning.sh"
  config.vm.network "private_network", ip: "ip address"
end
```

### Example of Provision
```shell
#!/usr/bin/env bash
apt-get -y clean
apt-get -y update
```

### reference
- http://rangken.github.io/blog/2015/vagrant-1/
- https://www.vagrantup.com/docs/
