Name:           ercole-server
Version:        ERCOLE_VERSION
Release:        1%{?dist}
Summary:        Ercole server

License:        Proprietary
URL:            https://github.com/amreo/%{name}
Source0:        https://github.com/amreo/%{name}/releases/download/%{version}/%{name}-%{version}.tar.gz
Requires:       java-11-openjdk

Group:          Tools

Buildroot: /tmp/rpm-ercole-server

%global debug_package %{nil}

%description
This is the server component for the Ercole project.

%pre
getent passwd ercole >/dev/null || \
    useradd -s /bin/bash -c "Ercole server user" ercole
exit 0

%prep
%setup -q -n %{name}-%{version}

rm -rf $RPM_BUILD_ROOT
mkdir -p $RPM_BUILD_ROOT/opt/ercole-server
install -m 755 ercole-server.jar $RPM_BUILD_ROOT/opt/ercole-server
install -d $RPM_BUILD_ROOT/etc/systemd/system
install -d $RPM_BUILD_ROOT/opt/ercole-server/run
install -m 644 package/rhel7/ercole-server.service $RPM_BUILD_ROOT/etc/systemd/system/ercole-server.service

%post

%files
%attr(-,ercole,-) /opt/ercole-server/run
%dir /opt/ercole-server
/etc/systemd/system/ercole-server.service
/opt/ercole-server/ercole-server.jar

%changelog
* Mon Aug 2 2019 Andrea Laisa <alaisa@sorint.it>
- 
