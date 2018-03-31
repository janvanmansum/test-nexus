Vagrant.configure(2) do |config|
   config.vm.define "test", autostart: true do |test|
      test.vm.box = "geerlingguy/centos7"
      test.vm.hostname = "test"
      test.vm.network :private_network, ip: "192.168.33.32"
   end
   config.vm.provision "ansible" do |ansible|
      ansible.playbook = "src/main/ansible/vagrant.yml"
      ansible.config_file = "src/main/ansible/ansible.cfg"
      ansible.compatibility_mode = "2.0"
      # ansible.verbose = "vvvv"
   end
   config.vm.provider "virtualbox" do |vb|
      vb.gui = false
      vb.memory = 3072
      vb.cpus = 2
      vb.customize ["guestproperty", "set", :id, "--timesync-threshold", "1000"]
      vb.customize ["guestproperty", "set", :id, "--timesync-interval", "1000"]
   end
end
